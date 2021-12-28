package ru.meighgen.commiter.sevice.consumer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.meighgen.commiter.sevice.CommitService;
import ru.meighgen.commiter.model.KafkaMsg;

/**
 * The type Commits consumer service.
 */
@Service
public class CommitsConsumerService {
    /**
     * The Commit service.
     */
    @Autowired
    CommitService commitService;

    /**
     * Print file.
     *
     * @param message the message
     */
    @KafkaListener(topics = "create_commits", groupId = "message_group_id")
    public void printFile(KafkaMsg message) {
        try {
            System.out.println(message.getBody());
            final JSONObject obj = new JSONObject(message.getBody());
            commitService.createCommit(
                    obj.getLong("branchId"), obj.getString("message"), obj.getString("folderId"), obj.getString("commitId")
            );

            List<File> list = new ArrayList<>();
            for (int i=0; i<obj.getJSONArray("filesToUpload").length(); i++) {
                list.add( new File(obj.getJSONArray("filesToUpload").getString(i)) );
            }

            commitService.uploadFilesToCommit(list, obj.getString("folderId"));

            if (obj.getBoolean("hasNeedToCopy")) {
                commitService.copyFilesFromCommitToCommit(obj.getString("oldCommitFolder"), obj.getString("folderId"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
