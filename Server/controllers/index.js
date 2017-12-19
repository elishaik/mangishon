var express = require('express');
var router = express.Router();
let verifyToken = require('../middlewares/verifyToken');

router.use('/user',require('./user'));
router.use('/challenge', require(('./challenge')));
//router.use('/protected', verifyToken, require('./protectedApi'));

module.exports = router;
