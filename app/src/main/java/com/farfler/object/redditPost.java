package com.farfler.object;

import java.util.List;
import java.util.Map;

public class redditPost {
    private String kind;
    private Data data;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        private String after;
        private int dist;
        private String modhash;
        private String geo_filter;
        private List<Child> children;
        private String before;

        public String getAfter() {
            return after;
        }

        public void setAfter(String after) {
            this.after = after;
        }

        public int getDist() {
            return dist;
        }

        public void setDist(int dist) {
            this.dist = dist;
        }

        public String getModhash() {
            return modhash;
        }

        public void setModhash(String modhash) {
            this.modhash = modhash;
        }

        public String getGeo_filter() {
            return geo_filter;
        }

        public void setGeo_filter(String geo_filter) {
            this.geo_filter = geo_filter;
        }

        public List<Child> getChildren() {
            return children;
        }

        public void setChildren(List<Child> children) {
            this.children = children;
        }

        public String getBefore() {
            return before;
        }

        public void setBefore(String before) {
            this.before = before;
        }

        public static class Child {
            private String kind;
            private ChildData data;

            public String getKind() {
                return kind;
            }

            public void setKind(String kind) {
                this.kind = kind;
            }

            public ChildData getData() {
                return data;
            }

            public void setData(ChildData data) {
                this.data = data;
            }

            public static class ChildData {
                private String subreddit;

                public String getSelftext() {
                    return selftext;
                }

                public String getTitle() {
                    return title;
                }

                private String selftext;
                private String author_fullname;
                private boolean saved;
                private int gilded;
                private boolean clicked;
                private String title;
                private List<Object> link_flair_richtext;
                private String subreddit_name_prefixed;
                private boolean hidden;
                private int downs;
                private boolean hide_score;
                private String name;
                private boolean quarantine;
                private String link_flair_text_color;
                private float upvote_ratio;
                private String subreddit_type;
                private int ups;
                private int total_awards_received;
                private Map<String, Object> media_embed;
                private boolean is_original_content;
                private List<Object> user_reports;
                private Object secure_media;
                private boolean is_reddit_media_domain;
                private boolean is_meta;
                private Object category;
                private Map<String, Object> secure_media_embed;
                private Object link_flair_text;
                private boolean can_mod_post;
                private int score;
                private boolean is_created_from_ads_ui;
                private boolean author_premium;
                private String thumbnail;
                private boolean edited;
                private List<Object> author_flair_richtext;
                private Map<String, Object> gildings;
                private boolean is_self;
                private long created;
                private String link_flair_type;
                private Object wls;
                private Object removed_by_category;
                private Object banned_by;
                private String author_flair_type;
                private String domain;
                private boolean allow_live_comments;
                private String selftext_html;
                private Object likes;
                private Object suggested_sort;
                private Object banned_at_utc;
                private Object view_count;
                private boolean archived;
                private boolean no_follow;
                private boolean is_crosspostable;
                private boolean pinned;
                private boolean over_18;
                private List<Object> all_awardings;
                private List<Object> awarders;
                private boolean media_only;
                private boolean can_gild;
                private boolean spoiler;
                private boolean locked;
                private Object author_flair_text;
                private List<Object> treatment_tags;
                private boolean visited;
                private Object removed_by;
                private Object num_reports;
                private Object distinguished;
                private String subreddit_id;
                private boolean author_is_blocked;
                private Object mod_reason_by;
                private Object removal_reason;
                private String link_flair_background_color;
                private String id;
                private boolean is_robot_indexable;
                private Object report_reasons;
                private String author;
                private Object discussion_type;
                private int num_comments;
                private boolean send_replies;
                private boolean contest_mode;
                private List<Object> mod_reports;
                private boolean author_patreon_flair;
                private Object author_flair_text_color;
                private String permalink;
                private boolean stickied;
                private String url;
                private int subreddit_subscribers;
                private long created_utc;
                private int num_crossposts;
                private Object media;
                private boolean is_video;

                // Getters and setters for all fields
                // ...
            }
        }
    }
}