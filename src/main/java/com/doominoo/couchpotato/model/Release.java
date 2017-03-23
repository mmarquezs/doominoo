
package com.doominoo.couchpotato.model;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Release {

    @SerializedName("files")
    private Files mFiles;
    @SerializedName("identifier")
    private String mIdentifier;
    @SerializedName("is_3d")
    private Boolean mIs3D;
    @SerializedName("last_edit")
    private Long mLastEdit;
    @SerializedName("media_id")
    private String mMediaId;
    @SerializedName("quality")
    private String mQuality;
    @SerializedName("status")
    private String mStatus;
    @SerializedName("_id")
    private String m_id;
    @SerializedName("_rev")
    private String m_rev;
    @SerializedName("_t")
    private String m_t;

    public Files getFiles() {
        return mFiles;
    }

    public String getIdentifier() {
        return mIdentifier;
    }

    public Boolean getIs3D() {
        return mIs3D;
    }

    public Long getLastEdit() {
        return mLastEdit;
    }

    public String getMediaId() {
        return mMediaId;
    }

    public String getQuality() {
        return mQuality;
    }

    public String getStatus() {
        return mStatus;
    }

    public String get_id() {
        return m_id;
    }

    public String get_rev() {
        return m_rev;
    }

    public String get_t() {
        return m_t;
    }

    public static class Builder {

        private Files mFiles;
        private String mIdentifier;
        private Boolean mIs3D;
        private Long mLastEdit;
        private String mMediaId;
        private String mQuality;
        private String mStatus;
        private String m_id;
        private String m_rev;
        private String m_t;

        public Release.Builder withFiles(Files files) {
            mFiles = files;
            return this;
        }

        public Release.Builder withIdentifier(String identifier) {
            mIdentifier = identifier;
            return this;
        }

        public Release.Builder withIs3D(Boolean is3D) {
            mIs3D = is3D;
            return this;
        }

        public Release.Builder withLastEdit(Long lastEdit) {
            mLastEdit = lastEdit;
            return this;
        }

        public Release.Builder withMediaId(String mediaId) {
            mMediaId = mediaId;
            return this;
        }

        public Release.Builder withQuality(String quality) {
            mQuality = quality;
            return this;
        }

        public Release.Builder withStatus(String status) {
            mStatus = status;
            return this;
        }

        public Release.Builder with_id(String _id) {
            m_id = _id;
            return this;
        }

        public Release.Builder with_rev(String _rev) {
            m_rev = _rev;
            return this;
        }

        public Release.Builder with_t(String _t) {
            m_t = _t;
            return this;
        }

        public Release build() {
            Release Release = new Release();
            Release.mFiles = mFiles;
            Release.mIdentifier = mIdentifier;
            Release.mIs3D = mIs3D;
            Release.mLastEdit = mLastEdit;
            Release.mMediaId = mMediaId;
            Release.mQuality = mQuality;
            Release.mStatus = mStatus;
            Release.m_id = m_id;
            Release.m_rev = m_rev;
            Release.m_t = m_t;
            return Release;
        }

    }

}
