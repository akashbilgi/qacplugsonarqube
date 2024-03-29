package nl.futureedge.sonar.plugin.issueresolver.ws;

import org.junit.Assert;
import org.junit.Test;
import org.sonar.api.server.ws.WebService;
import org.sonar.api.server.ws.WebService.Action;
import org.sonar.api.server.ws.WebService.Controller;

public class IssueResolverWebServiceTest {

	@Test
	public void test() {
		final IssueResolverWebService subject = new IssueResolverWebService(new RunAction(), new ImportAction(),
				new UpdateAction());

		final WebService.Context context = new WebService.Context();
		Assert.assertEquals(0, context.controllers().size());
		subject.define(context);
		Assert.assertEquals(1, context.controllers().size());

		// Controller
		Controller controller = context.controller("api/issueresolver");
		Assert.assertNotNull(controller);
		Assert.assertEquals(3, controller.actions().size());

		// Export
		Action exportAction = controller.action("export");
		Assert.assertNotNull(exportAction);
		Assert.assertTrue(exportAction.handler() instanceof RunAction);
		Assert.assertEquals(1, exportAction.params().size());
		Assert.assertNotNull(exportAction.param("projectKey"));

		// Import
		Action importAction = controller.action("import");
		Assert.assertNotNull(importAction);
		Assert.assertTrue(importAction.handler() instanceof ImportAction);
		Assert.assertEquals(5, importAction.params().size());
		Assert.assertNotNull(importAction.param("projectKey"));
		Assert.assertNotNull(importAction.param("preview"));
		Assert.assertNotNull(importAction.param("data"));
		Assert.assertNotNull(importAction.param("skipAssign"));
		Assert.assertNotNull(importAction.param("skipComments"));

		// Update
		Action updateAction = controller.action("update");
		Assert.assertNotNull(updateAction);
		Assert.assertTrue(updateAction.handler() instanceof UpdateAction);
		Assert.assertEquals(5, updateAction.params().size());
		Assert.assertNotNull(updateAction.param("fromProjectKey"));
		Assert.assertNotNull(updateAction.param("projectKey"));
		Assert.assertNotNull(updateAction.param("preview"));
		Assert.assertNotNull(updateAction.param("skipAssign"));
		Assert.assertNotNull(updateAction.param("skipComments"));
	}

}
