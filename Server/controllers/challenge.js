let express = require('express');
let router = express.Router();
let Challenge = require('../models/challenge');
let Validations = require('../commons/validations');

router.get('/', function(req, res){
  Challenge.getChallenges(function(err,challenge){
    if (err){
      throw err;
    }
    res.json(challenge);
  });
});

router.get('/:_id', function(req, res){
  var id = req.params._id;
  var isIdAValidMongoObjectId = Validations.validateObjectId(id);
  if (isIdAValidMongoObjectId) {
    Challenge.getChallengeById(id, function(err,challenge){
      if (err){
        throw err;
      }
      if (!challenge){
        res.status(404).json('Id ' + id + ' not found');
      } else {
        res.json(challenge);
      }
    });
  } else {
    res.status(400).json('Id ' + id + ' is not a valid objectId');
  }
});

router.post('/', function(req, res){
  var challenge = req.body;
  Challenge.addChallenge(challenge, function(err,challenge){
    if (err){
      throw err;
    }
    res.json(challenge);
  });
});

router.put('/:_id', function(req, res){
  var id = req.params._id;
  var isIdAValidMongoObjectId = Validations.validateObjectId(id);
  if (isIdAValidMongoObjectId){
    var challenge = req.body;
    console.log(challenge);
    var returnUpdatedChallengeOption = { new : true };
    Challenge.updateChallenge(id, challenge, returnUpdatedChallengeOption, function(err,challenge){
      if (err){
        throw err;
      }
      res.json(challenge);
    });
  } else {
    res.status(400).json('Id ' + id + ' is not a valid objectId');
  }
});

router.delete('/:_id', function(req, res){
  var id = req.params._id;
  var isIdAValidMongoObjectId = Validations.validateObjectId(id);
  if (isIdAValidMongoObjectId){
    Challenge.deleteChallenge(id, function(err,challenge){
      if (err){
        throw err;
      }
      res.json(challenge);
    });
  } else {
    res.status(400).json('Id ' + id + ' is not a valid objectId');
  }
});

module.exports = router;
