/**
 * Set of utilities to use Ace Stream Player
 */
package org.acestream.sdk.player.api;

import android.content.Intent;
import androidx.annotation.NonNull;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.acestream.sdk.AceStream;

public class AceStreamPlayer {
    // Extras that can be passed to player
    public final static String EXTRA_LIBVLC_OPTIONS = "libvlc_options";
    public final static String EXTRA_SHOW_TV_UI = "show_tv_ui";
    public final static String EXTRA_ASK_RESUME = "ask_resume";
    public final static String EXTRA_PLAY_FROM_START = "play_from_start";
    public final static String EXTRA_PLAY_FROM_TIME = "play_from_time";
    public final static String EXTRA_AUDIO_DIGITAL_OUTPUT_ENABLED = "audio_digital_output_enabled";
    public final static String EXTRA_AUDIO_OUTPUT = "audio_output";
    public final static String EXTRA_PLAYLIST = "playlist";
    public final static String EXTRA_PLAYLIST_POSITION = "playlist_position";
    public final static String EXTRA_REMOTE_CLIENT_ID = "remote_client_id";
    public final static String EXTRA_BROADCAST_ACTION = "register_broadcast_action";

    // Extras that are used in broadcasts generated by player
    public final static String BROADCAST_EXTRA_EVENT = "event";
    public final static String BROADCAST_EXTRA_PLAYLIST_POSITION = "playlist_position";
    public final static String BROADCAST_EXTRA_MEDIA_URI = "media_uri";
    public final static String BROADCAST_EXTRA_MEDIA_ID = "media_id";
    public final static String BROADCAST_EXTRA_MEDIA_TIME = "media_time";
    public final static String BROADCAST_EXTRA_MEDIA_DURATION = "media_duration";
    public final static String BROADCAST_EXTRA_RENDERER = "renderer";
    public final static String BROADCAST_EXTRA_PREF_NAME = "pref_name";
    public final static String BROADCAST_EXTRA_PREF_VALUE = "pref_value";
    public final static String BROADCAST_EXTRA_REPEAT_TYPE = "repeat_type";
    public final static String BROADCAST_EXTRA_SHUFFLE = "shuffle";
    public final static String BROADCAST_EXTRA_SLEEP_TIME = "sleep_time";
    public final static String BROADCAST_EXTRA_MEDIA_FILE = "media_file";

    // Possible values of BROADCAST_EXTRA_EVENT
    public final static String BROADCAST_EVENT_PLAYER_STARTED = "player_started";
    public final static String BROADCAST_EVENT_PLAYER_STOPPED = "player_stopped";
    public final static String BROADCAST_EVENT_PLAYBACK_STARTED = "playback_started";
    public final static String BROADCAST_EVENT_CHANGE_RENDERER = "change_renderer";
    public final static String BROADCAST_EVENT_SAVE_METADATA = "save_metadata";
    public final static String BROADCAST_EVENT_UPDATE_PREFERENCE = "update_preference";
    public final static String BROADCAST_EVENT_SET_REPEAT_TYPE = "set_repeat_type";
    public final static String BROADCAST_EVENT_SET_SHUFFLE = "set_shuffle";
    public final static String BROADCAST_EVENT_SET_SLEEP_TIMER = "set_sleep_timer";

    public static Intent getPlayerIntent() {
        return new Intent(AceStream.ACTION_START_PLAYER);
    }

    // Tools to pass playlist to player
    // Playlist is passed as JSON-serialized array of PlaylistItem objects
    // Serialization is done with Gson
    public static class PlaylistItem {
        public String uri;
        public String title;
        public long id;

        public PlaylistItem(String aUri, String aTitle) {
            this(aUri, aTitle, 0);
        }

        public PlaylistItem(String aUri, String aTitle, long aId) {
            uri = aUri;
            title = aTitle;
            id = aId;
        }
    }

    public static class Playlist {
        @NonNull
        public static PlaylistItem[] fromJson(String jsonData) {
            if(TextUtils.isEmpty(jsonData)) {
                return new PlaylistItem[0];
            }

            return new Gson().fromJson(
                    jsonData,
                    new TypeToken<PlaylistItem[]>() {
                    }.getType()
            );
        }

        public static String toJson(PlaylistItem[] playlist) {
            return new Gson().toJson(playlist);
        }

        public static String fromSingleItem(String uri, String title, long id) {
            PlaylistItem[] playlist = new PlaylistItem[1];
            playlist[0] = new PlaylistItem(uri, title, id);
            return toJson(playlist);
        }
    }
}
