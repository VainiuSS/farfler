package com.farfler.object;

import java.util.List;
import java.util.Map;

public class redditPost {
    private String kind;
    private Data data;

    public Data getData() {
        return data;
    }

    public static class Data {
        private String after;
        private int dist;
        private String modhash;
        private String geo_filter;
        private List<Child> children;

        public List<Child> getChildren() {
            return children;
        }

        public static class Child {
            private String kind;
            private ChildData data;

            public static class ChildData {
                private Object approved_at_utc;
                private String subreddit;
                private String selftext;
                private String author_fullname;
                private boolean saved;
                private Object mod_reason_title;
                private int gilded;
                private boolean clicked;
                private String title;
                private List<Object> link_flair_richtext;
                private String subreddit_name_prefixed;
                private boolean hidden;
                private Object pwls;
                private Object link_flair_css_class;
                private int downs;
                private Object top_awarded_type;
                private boolean hide_score;
                private String name;
                private boolean quarantine;
                private String link_flair_text_color;
                private double upvote_ratio;
                private Object author_flair_background_color;
                private String subreddit_type;
                private int ups;
                private int total_awards_received;
                private Map<String, Object> media_embed;
                private Object author_flair_template_id;
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
                private Object approved_by;
                private boolean is_created_from_ads_ui;
                private boolean author_premium;
                private String thumbnail;
                private boolean edited;
                private Object author_flair_css_class;
                private List<Object> author_flair_richtext;
                private Map<String, Object> gildings;
                private Object content_categories;
                private boolean is_self;
                private Object mod_note;
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
            }
        }
    }
}