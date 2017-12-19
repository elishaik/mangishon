var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var Challenge = module.exports = mongoose.model('Challenge', new Schema({
    word: String,
    sentence: String,
    imageUrl: String,
    audioUrl: String,
}));

module.exports.getChallenges = function(callback, limit){
  Challenge.find(callback).limit(limit);
};

module.exports.getChallengeById = function(id, callback){
  Challenge.findById(id, callback);
};

module.exports.addChallenge = function(challenge, callback){
  Challenge.create(challenge, callback);
};

module.exports.updateChallenge = function(id, challenge, options, callback){
  var query = {_id : id};
  var update = {
    word: challenge.word,
    sentence: challenge.sentence,
    imageUrl: challenge.imageUrl,
    audioUrl: challenge.audioUrl
  };
  Challenge.findOneAndUpdate(query, update, options, callback);
};

module.exports.deleteChallenge = function(id, callback){
  var query = {_id : id};
  Challenge.remove(query, callback);
};
