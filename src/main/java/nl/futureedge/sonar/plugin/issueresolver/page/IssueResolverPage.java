package nl.futureedge.sonar.plugin.issueresolver.page;

import org.sonar.api.web.page.Context;
import org.sonar.api.web.page.Page;
import org.sonar.api.web.page.Page.Qualifier;
import org.sonar.api.web.page.Page.Scope;
import org.sonar.api.web.page.PageDefinition;

/**
 * Issue resolver page.
 */
public final class IssueResolverPage implements PageDefinition {

	@Override
	public void define(final Context context) {
		//final Page issueresolverPage = Page.builder("issueresolver/entrypoint").setName("QAC plugin")
		//		.setComponentQualifiers(Qualifier.VIEW).setScope(Scope.COMPONENT).setAdmin(false).build();//.setComponentQualifiers(Qualifier.PROJECT)
		context.addPage(Page.builder("issueresolver/entrypoint").setName("QAC plugin").setComponentQualifiers(Qualifier.PROJECT).setScope(Scope.COMPONENT).setAdmin(false).build());
		context.addPage(Page.builder("issueresolver/entrypoint2").setName("QAC plugin1").setScope(Scope.GLOBAL).setAdmin(false).build());
	}
}
