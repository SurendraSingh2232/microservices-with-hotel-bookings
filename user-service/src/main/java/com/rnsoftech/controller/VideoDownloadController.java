package com.rnsoftech.controller;

import com.rnsoftech.service.impl.VideoDownloadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class VideoDownloadController {

    private final Logger log = LoggerFactory.getLogger(VideoDownloadController.class);

    @Autowired
    private VideoDownloadService videoDownloadService;

    @GetMapping("/download")
    public String downloadYouTubeVideo(@RequestParam String videoUrl) {
        log.info("REST Request to downloadYouTubeVideo: {}", videoUrl);
        try {
            String downloadPath = "C:/Users/hukam/Downloads/%(title)s.%(ext)s";
            videoDownloadService.downloadVideo(videoUrl, downloadPath);
            return "Video downloaded successfully!";
        } catch (Exception e) {
            return "Error downloading video: " + e.getMessage();
        }
    }
}
