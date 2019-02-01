package ru.stqa.pft.rest;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static com.sun.javafx.runtime.async.BackgroundExecutor.getExecutor;
import static org.testng.Assert.assertEquals;

public class RestTests extends TestBase{

    @Test
    public void testCreateIssue() throws IOException {
        boolean issueOpen = isIssueOpen(737);
        if(issueOpen == false) {
            skipIfNotFixed(737);
        }
        Set<Issue> oldIssues = getIssues();
        Issue newIssue = new Issue().withSubject("test issue Ritka").withDescription("new test issue");
        int issueId = create(newIssue);
        Set<Issue> newIssues = getIssues();
        oldIssues.add(newIssue.withId(issueId));
        assertEquals(newIssues, oldIssues);
    }

    private Set<Issue> getIssues() throws IOException {
        boolean issueOpen = isIssueOpen(736);
        if(issueOpen == false) {
            skipIfNotFixed(736);
        }
        String json = getExecutor().execute(Request.Get("http://bugify.stqa.ru/api/issues.json?limit=1000"))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken <Set<Issue>>(){}.getType());
    }

    private int create(Issue newIssue) throws IOException {
        String json = getExecutor().execute(Request.Post("http://bugify.stqa.ru/api/issues.json")
                .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                          new BasicNameValuePair("description", newIssue.getDescription())))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();

    }
   // protected Executor getExecutor() {
     //   return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
   // }
}
