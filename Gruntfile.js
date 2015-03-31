modules.export = function(grunt) {

  grunt.initConfig({
    pkg: grunt.file.readJSON('package.json'),
    
    concat: {

    }
  });

  grunt.loadNpmTasks('grunt-contrib-concat');

  //grunt.registerTask('default', ['concat']);

};
