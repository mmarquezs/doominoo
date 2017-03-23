
package com.doominoo.couchpotato.model;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class DownloadInfo {

    @SerializedName("downloader")
    private String mDownloader;
    @SerializedName("id")
    private String mId;
    @SerializedName("status_support")
    private Boolean mStatusSupport;

    public String getDownloader() {
        return mDownloader;
    }

    public String getId() {
        return mId;
    }

    public Boolean getStatusSupport() {
        return mStatusSupport;
    }

    public static class Builder {

        private String mDownloader;
        private String mId;
        private Boolean mStatusSupport;

        public DownloadInfo.Builder withDownloader(String downloader) {
            mDownloader = downloader;
            return this;
        }

        public DownloadInfo.Builder withId(String id) {
            mId = id;
            return this;
        }

        public DownloadInfo.Builder withStatusSupport(Boolean statusSupport) {
            mStatusSupport = statusSupport;
            return this;
        }

        public DownloadInfo build() {
            DownloadInfo DownloadInfo = new DownloadInfo();
            DownloadInfo.mDownloader = mDownloader;
            DownloadInfo.mId = mId;
            DownloadInfo.mStatusSupport = mStatusSupport;
            return DownloadInfo;
        }

    }

}
