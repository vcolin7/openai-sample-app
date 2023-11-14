package org.example;

import com.generic.core.credential.KeyCredential;
import com.openai.OpenAIClient;
import com.openai.OpenAIClientBuilder;
import com.openai.models.ChatCompletionRequestMessage;
import com.openai.models.ChatCompletionRequestMessageRole;
import com.openai.models.CreateChatCompletionRequest;
import com.openai.models.CreateChatCompletionRequestModel;
import com.openai.models.CreateChatCompletionResponse;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        OpenAIClient openAIClient = new OpenAIClientBuilder()
            .credential(new KeyCredential(System.getenv("OPENAI_SECRET_KEY")))
            .buildClient();

        List<ChatCompletionRequestMessage> messages = new ArrayList<>();
        messages.add(new ChatCompletionRequestMessage(ChatCompletionRequestMessageRole.SYSTEM,
            "You are a helpful assistant."));
        messages.add(new ChatCompletionRequestMessage(ChatCompletionRequestMessageRole.USER,
            "Who won the world series in 2020?"));
        messages.add(new ChatCompletionRequestMessage(ChatCompletionRequestMessageRole.ASSISTANT,
            "The Los Angeles Dodgers won the World Series in 2020."));
        messages.add(new ChatCompletionRequestMessage(ChatCompletionRequestMessageRole.USER,
            "Where was it played?"));

        CreateChatCompletionRequest createChatCompletionRequest =
            new CreateChatCompletionRequest(CreateChatCompletionRequestModel.GPT_3_5_TURBO, messages);

        CreateChatCompletionResponse createChatCompletionResponse =
            openAIClient.createChatCompletion(createChatCompletionRequest);

        System.out.println("Retrieved the following choices for a chat completion:");

        createChatCompletionResponse.getChoices()
            .forEach(choice -> System.out.println(choice.getMessage().getContent()));
    }
}