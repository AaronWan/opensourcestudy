package script;

import com.google.common.base.Splitter;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.util.stream.Stream;

public class MQScriptCreate {
    public static void main(String[] args) {
        String data="FLOW-SVIP-BATCH FLOW-SVIP-BATCH-C 80\n" +
                "FLOW-SVIP-NORMAL FLOW-SVIP-NORMAL-C 80\n" +
                "FLOW-SVIP-NORMAL-SLOW FLOW-SVIP-NORMAL-SLOW-C 80\n" +
                "FLOW-SVIP-BATCH-SLOW FLOW-SVIP-BATCH-SLOW-C 80\n";
        printScript(data);
    }
    public static void printScript(String data){
        @Data
        class MQScript{
            private String topic;
            private String consumerGroup;
            private String nameserver="";
            private Integer queueSize;

            public MQScript(String text) {
                String[] config=text.split(" ");
                topic = config[0];
                consumerGroup = config[1];
                queueSize = Integer.parseInt(config[2]);
            }

            @Override
            public String toString() {
                return "./mqadmin updateTopic -c FS-MQ -t "+topic+" -n "+nameserver+"-w "+queueSize+"-r "+queueSize+"\n" +
                        "./mqadmin updateSubGroup -c FS-MQ -g "+consumerGroup+" -n "+nameserver+"\n";
            }
        }
        Splitter.on("\n").splitToList(data).stream().filter(StringUtils::isNotEmpty).map(MQScript::new).forEach(System.out::println);
    }


}
