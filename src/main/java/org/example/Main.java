package org.example;

import com.generic.core.credential.KeyCredential;
import com.generic.core.http.Response;
import com.generic.core.implementation.http.serializer.DefaultJsonSerializer;
import com.generic.core.models.BinaryData;
import com.openai.OpenAIClient;
import com.openai.OpenAIClientBuilder;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        OpenAIClient openAIClient = new OpenAIClientBuilder()
            .credential(new KeyCredential(System.getenv("OPENAI_SECRET_KEY")))
            .buildClient();

        List<ChatCompletionParameters.Message> messages = new ArrayList<>();
        messages.add(new ChatCompletionParameters.Message("system", "You are a helpful assistant."));
        messages.add(new ChatCompletionParameters.Message("user", "Who won the world series in 2020?"));
        messages.add(new ChatCompletionParameters.Message("user", "The Los Angeles Dodgers won the World Series in 2020."));
        messages.add(new ChatCompletionParameters.Message("user", "Where was it played?"));

        ChatCompletionParameters parameters = new ChatCompletionParameters("gpt-3.5-turbo", messages);

        Response<BinaryData> chatCompletionResponse =
            openAIClient.createChatCompletionWithResponse(
                BinaryData.fromBytes(
                    new DefaultJsonSerializer().serializeToBytes(parameters)), null);

        System.out.println("Request completed with status code: " + chatCompletionResponse.getStatusCode());
        System.out.println("Response value: " + chatCompletionResponse.getValue().toString());
    }
}