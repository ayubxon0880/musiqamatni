package uz.musiqamatni.musiqamatni.config;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
@RequiredArgsConstructor
public class ScheduledTasks {
    private final Logger LOG = LoggerFactory.getLogger(ScheduledTasks.class);
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

//    @Scheduled(cron = "0 0 6 1/1 * ? *")
    public void getData() {
//        WebClient webClient = WebClient.create();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
//        headers.set("Authorization", "Bearer YOUR_ACCESS_TOKEN"); // O'zgartiring
//
        // So'rovni yuborish va headerlarni qo'shish
//        String responseBody = webClient.post()
//                .uri(apiUrl)
//                .contentType(MediaType.MULTIPART_FORM_DATA)
//                .headers(httpHeaders -> httpHeaders.addAll(headers))
//                .body(BodyInserters.fromMultipartData("file", fileResource))
//                .retrieve()
//                .bodyToMono(String.class)
//                .block();
    }
}
