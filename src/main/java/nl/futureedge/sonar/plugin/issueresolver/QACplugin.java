package nl.futureedge.sonar.plugin.issueresolver;

import org.sonar.api.Plugin;
import org.sonar.api.SonarQubeSide;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;

import nl.futureedge.sonar.plugin.issueresolver.page.IssueResolverPage;
import nl.futureedge.sonar.plugin.issueresolver.ws.*;
import nl.futureedge.sonar.plugin.issueresolver.ws.ImportAction;
import nl.futureedge.sonar.plugin.issueresolver.ws.IssueResolverWebService;
import nl.futureedge.sonar.plugin.issueresolver.ws.UpdateAction;

/**
 * Issue resolver plugin.
 */
public final class QACplugin implements Plugin {

	private static final Logger LOGGER = Loggers.get(RunAction.class);

	@Override
	public void define(final Context context) {
		if (SonarQubeSide.SERVER == context.getRuntime().getSonarQubeSide()) {
			LOGGER.info("Defining plugin ...");
			context.addExtension(RunAction.class);//, ImportAction.class, UpdateAction.class);
			context.addExtensions(IssueResolverPage.class, IssueResolverWebService.class);
			LOGGER.info("Plugin defined");
		}
	}
}
