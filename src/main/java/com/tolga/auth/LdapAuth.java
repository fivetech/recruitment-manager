package com.tolga.auth;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

public class LdapAuth {
	private Hashtable<Object, Object> env = new Hashtable<>();

	public LdapAuth() {

		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://localhost:10389/");
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, "uid=admin,ou=system");
		env.put(Context.SECURITY_CREDENTIALS, "your_pass");

	}

	public boolean authenticate(String email, String password) {
		try {
			DirContext ctx = new InitialDirContext(env);

			String base = "dc=example,dc=com";
			String filter = "(&(objectClass=inetOrgPerson)(uid={0}))";
			SearchControls ctls = new SearchControls();
			ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			ctls.setReturningAttributes(new String[0]);
			ctls.setReturningObjFlag(true);
			NamingEnumeration<SearchResult> enm = ctx.search(base, filter, new String[] { email }, ctls);

			String dn = null;

			if (enm.hasMore()) {
				SearchResult result = (SearchResult) enm.next();
				dn = result.getNameInNamespace();

				System.out.println("dn: " + dn);
			}

			if (dn == null || enm.hasMore()) {
				return false;
			}

			// Step 3: Bind with found DN and given password
			ctx.addToEnvironment(Context.SECURITY_PRINCIPAL, dn);
			ctx.addToEnvironment(Context.SECURITY_CREDENTIALS, password);
			// Perform a lookup in order to force a bind operation with JNDI
			ctx.lookup(dn);
			System.out.println("Authentication successful");
			enm.close();
			ctx.close();
			return true;
		} catch (NamingException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

}
