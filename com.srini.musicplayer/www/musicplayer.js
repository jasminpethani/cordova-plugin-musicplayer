var exec = require('cordova/exec');

function MusicPlayer() {
    
}

MusicPlayer.prototype.init = function(successCallback, errorCallback, json) {
    exec(successCallback, errorCallback, "MusicPlayer", "init", [json]);
};

MusicPlayer.prototype.getMusicList = function(successCallback, errorCallback, json) {
    exec(successCallback, errorCallback, "MusicPlayer", "getMusicList", [json]);
};

MusicPlayer.prototype.getAlbums = function(successCallback, errorCallback, json) {
    exec(successCallback, errorCallback, "MusicPlayer", "getAlbums", [json]);
};

MusicPlayer.prototype.getArtists = function(successCallback, errorCallback, json) {
    exec(successCallback, errorCallback, "MusicPlayer", "getArtists", [json]);
};

MusicPlayer.prototype.playSong = function(successCallback, errorCallback, json) {
    exec(successCallback, errorCallback, "MusicPlayer", "playSong", [json]);
};

MusicPlayer.prototype.pause = function(successCallback, errorCallback, json) {
    exec(successCallback, errorCallback, "MusicPlayer", "pause", [json]);
};

MusicPlayer.prototype.resume = function(successCallback, errorCallback, json) {
    exec(successCallback, errorCallback, "MusicPlayer", "resume", [json]);
};

MusicPlayer.prototype.changeSong = function(successCallback, errorCallback, json) {
    exec(successCallback, errorCallback, "MusicPlayer", "changeSong", [json]);
};

MusicPlayer.prototype.currentTrackInfo = function(successCallback, errorCallback, json) {
    exec(successCallback, errorCallback, "MusicPlayer", "currentTrackInfo", [json]);
};

module.exports = new MusicPlayer();
