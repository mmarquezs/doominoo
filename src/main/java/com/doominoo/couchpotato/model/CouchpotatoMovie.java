
package com.doominoo.couchpotato.model;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.util.List;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class CouchpotatoMovie {

    @SerializedName("category_id")
    private Object mCategoryId;
    @SerializedName("files")
    private Files mFiles;
    @SerializedName("identifiers")
    private Identifiers mIdentifiers;
    @SerializedName("info")
    private Info mInfo;
    @SerializedName("last_edit")
    private Long mLastEdit;
    @SerializedName("profile_id")
    private Object mProfileId;
    @SerializedName("releases")
    private List<Release> mReleases;
    @SerializedName("status")
    private String mStatus;
    @SerializedName("tags")
    private List<Object> mTags;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("type")
    private String mType;
    @SerializedName("_id")
    private String m_id;
    @SerializedName("_rev")
    private String m_rev;
    @SerializedName("_t")
    private String m_t;

    public Object getCategoryId() {
        return mCategoryId;
    }

    public Files getFiles() {
        return mFiles;
    }

    public Identifiers getIdentifiers() {
        return mIdentifiers;
    }

    public Info getInfo() {
        return mInfo;
    }

    public Long getLastEdit() {
        return mLastEdit;
    }

    public Object getProfileId() {
        return mProfileId;
    }

    public List<Release> getReleases() {
        return mReleases;
    }

    public String getStatus() {
        return mStatus;
    }

    public List<Object> getTags() {
        return mTags;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getType() {
        return mType;
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

        private Object mCategoryId;
        private Files mFiles;
        private Identifiers mIdentifiers;
        private Info mInfo;
        private Long mLastEdit;
        private Object mProfileId;
        private List<Release> mReleases;
        private String mStatus;
        private List<Object> mTags;
        private String mTitle;
        private String mType;
        private String m_id;
        private String m_rev;
        private String m_t;

        public CouchpotatoMovie.Builder withCategoryId(Object categoryId) {
            mCategoryId = categoryId;
            return this;
        }

        public CouchpotatoMovie.Builder withFiles(Files files) {
            mFiles = files;
            return this;
        }

        public CouchpotatoMovie.Builder withIdentifiers(Identifiers identifiers) {
            mIdentifiers = identifiers;
            return this;
        }

        public CouchpotatoMovie.Builder withInfo(Info info) {
            mInfo = info;
            return this;
        }

        public CouchpotatoMovie.Builder withLastEdit(Long lastEdit) {
            mLastEdit = lastEdit;
            return this;
        }

        public CouchpotatoMovie.Builder withProfileId(Object profileId) {
            mProfileId = profileId;
            return this;
        }

        public CouchpotatoMovie.Builder withReleases(List<Release> releases) {
            mReleases = releases;
            return this;
        }

        public CouchpotatoMovie.Builder withStatus(String status) {
            mStatus = status;
            return this;
        }

        public CouchpotatoMovie.Builder withTags(List<Object> tags) {
            mTags = tags;
            return this;
        }

        public CouchpotatoMovie.Builder withTitle(String title) {
            mTitle = title;
            return this;
        }

        public CouchpotatoMovie.Builder withType(String type) {
            mType = type;
            return this;
        }

        public CouchpotatoMovie.Builder with_id(String _id) {
            m_id = _id;
            return this;
        }

        public CouchpotatoMovie.Builder with_rev(String _rev) {
            m_rev = _rev;
            return this;
        }

        public CouchpotatoMovie.Builder with_t(String _t) {
            m_t = _t;
            return this;
        }

        public CouchpotatoMovie build() {
            CouchpotatoMovie CouchpotatoMovie = new CouchpotatoMovie();
            CouchpotatoMovie.mCategoryId = mCategoryId;
            CouchpotatoMovie.mFiles = mFiles;
            CouchpotatoMovie.mIdentifiers = mIdentifiers;
            CouchpotatoMovie.mInfo = mInfo;
            CouchpotatoMovie.mLastEdit = mLastEdit;
            CouchpotatoMovie.mProfileId = mProfileId;
            CouchpotatoMovie.mReleases = mReleases;
            CouchpotatoMovie.mStatus = mStatus;
            CouchpotatoMovie.mTags = mTags;
            CouchpotatoMovie.mTitle = mTitle;
            CouchpotatoMovie.mType = mType;
            CouchpotatoMovie.m_id = m_id;
            CouchpotatoMovie.m_rev = m_rev;
            CouchpotatoMovie.m_t = m_t;
            return CouchpotatoMovie;
        }

    }

}
