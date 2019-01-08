package nl.futureedge.sonar.plugin.issueresolver.ws;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.sonar.api.server.ws.Request;
import org.sonar.api.server.ws.Response;
import org.sonar.api.server.ws.WebService.NewAction;
import org.sonar.api.server.ws.WebService.NewController;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;
import org.sonar.api.utils.text.JsonWriter;
import org.sonarqube.ws.Issues.Component;
import org.sonarqube.ws.Issues.Issue;

import nl.futureedge.sonar.plugin.issueresolver.helper.IssueHelper;
import nl.futureedge.sonar.plugin.issueresolver.helper.SearchHelper;
import nl.futureedge.sonar.plugin.issueresolver.issues.IssueData;
import nl.futureedge.sonar.plugin.issueresolver.issues.IssueKey;

/**
 * Export action.
 */
public class RunAction implements IssueResolverWsAction {

	public static final String ACTION = "export";
	public static final String PARAM_PROJECT_KEY = "projectKey";

	private static final Logger LOGGER = Loggers.get(RunAction.class);

	@Override
	public void define(final NewController controller) {
		LOGGER.debug("Defining export action ...");
		final NewAction action = controller.createAction(ACTION)
				.setDescription("Run QAC instance")
				.setResponseExample(getClass().getResource("/response-examples/export.json")).setHandler(this)
				.setPost(false);

		action.createParam(PARAM_PROJECT_KEY).setDescription("Project to export issues for")
				.setExampleValue("my-project").setRequired(true);
		LOGGER.debug("Export action defined");
	}

	@Override
	public void handle(final Request request, final Response response) {
		try {
			Process p = Runtime.getRuntime().exec(new String[] {"cmd", "/C", "run.bat"});//works
			}
			catch (Exception e){
				   e.printStackTrace();
				}
		
		LOGGER.debug("Getting project loaction..");//Handle issueresolver export request
		//response.setHeader("Content-Disposition", "attachment; filename=\"resolved-issues.json\"");

		//final JsonWriter responseWriter = response.newJsonWriter();
		//writeStart(responseWriter);

		//IssueHelper.forEachIssue(request.localConnector(),
		//		SearchHelper.findIssuesForExport(request.mandatoryParam(PARAM_PROJECT_KEY)), (searchIssuesResponse,
		//				issue) -> writeIssue(responseWriter, issue, searchIssuesResponse.getComponentsList()));

		//writeEnd(responseWriter);
		//responseWriter.close();
		LOGGER.debug("Running QAC");//Issueresolver export request done
	}

	private void writeStart(final JsonWriter writer) {
		
			
		
		writer.beginObject();
		//writer.prop("version", 1);
	//	writer.name("Running QAC, wait!!");
		writer.beginArray();
	}

	private void writeIssue(final JsonWriter writer, final Issue issue, List<Component> components) {
		writer.beginObject();

		final IssueKey key = IssueKey.fromIssue(issue, components);
		key.write(writer);

		final IssueData data = IssueData.fromIssue(issue);
		data.write(writer);

		writer.endObject();
	}

	private void writeEnd(final JsonWriter writer) {
		writer.endArray();
		writer.endObject();
		writer.close();
	}

}
