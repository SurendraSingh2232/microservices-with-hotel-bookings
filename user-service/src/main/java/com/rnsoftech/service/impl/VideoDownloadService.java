package com.rnsoftech.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Arrays;

@Service
public class VideoDownloadService {

    private final Logger log = LoggerFactory.getLogger(VideoDownloadService.class);

    public void downloadVideo(String videoUrl, String destinationFilePath) throws Exception {
        log.info("Service Request to downloadVideo: {}, {}", videoUrl, destinationFilePath);

        String ytDlpPath = "C:/Users/hukam/Downloads/yt-dlp.exe";
        String downloadPath = "C:/Users/hukam/Downloads/%(title)s.%(ext)s";

        String[] command = {ytDlpPath, "-o", downloadPath, videoUrl};
        System.out.println("Executing command: " + Arrays.toString(command));

        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.inheritIO();
            Process process = processBuilder.start();
            int exitCode = process.waitFor();

            if (exitCode != 0) {
                throw new Exception("yt-dlp failed to download the video. Exit code: " + exitCode);
            }
            System.out.println("Video downloaded successfully to: " + downloadPath);
        } catch (IOException | InterruptedException e) {
            throw new Exception("Error executing yt-dlp: " + e.getMessage());
        }
    }
}
