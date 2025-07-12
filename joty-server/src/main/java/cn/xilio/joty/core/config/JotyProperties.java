package cn.xilio.joty.core.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@Component
@ConfigurationProperties(prefix = "joty")
public class JotyProperties {
    private Boolean demoModel = false;
    private Code code = new Code();
    private Task task = new Task();
    private File file = new File();

    @Getter
    @Setter
    static class Code {
        private Integer maxTryTimes = 10;
    }

    @Getter
    @Setter
    static class Task {
        private String expireUrlClean = "0 0 0 * * ?";
    }

    @Getter
    @Setter
    static class File {
        private UploadModel uploadModel = UploadModel.Local;
        private String localPath;
        private String publicPath;

        enum UploadModel {
            Local,
            MinIO
        }
    }
}
