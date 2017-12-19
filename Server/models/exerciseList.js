var mongoose = require('mongoose');
var Schema = mongoose.Schema;

module.exports = mongoose.model('ExerciseList', new Schema({
    exercises: [{
      type: mongoose.Schema.Types.ObjectId,
      ref: 'Exercise'
    }]
}));
