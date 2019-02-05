package ru.stqa.pft.rest;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;


public class TestBase {


    public boolean isIssueOpen(int issueId) throws IOException {
        if( state_name(issueId).equals("Resolved")){
            return true;
        } else
        {return false;}
    }

    public String state_name(int issueId) throws IOException {
        String json = getExecutor().execute(Request.Get("http://bugify.stqa.ru/api/issues/"  + issueId +".json"))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement list = parsed.getAsJsonObject().get("issues");
        JsonElement issue = list.getAsJsonArray().get(0);
        JsonElement issueState = issue.getAsJsonObject().get("state_name");
        return new Gson().fromJson(issueState, new TypeToken<String>() {
        }.getType());

    }


    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    protected Executor getExecutor() {
        return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
    }
}
