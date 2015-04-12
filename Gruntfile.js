module.exports = function(grunt) {
  grunt.initConfig({
    pkg: grunt.file.readJSON('package.json'),
    clean: ['public/app/volt.js'],
    concat: {
      dist: {
        src: ['public/app/**/*.js'],
        dest: 'public/app/volt.js'
      }
    },
    watch: {
      files: ['public/app/**/*.js', '!public/app/volt.js'],
      tasks: ['clean', 'concat']
    }
  });
  grunt.loadNpmTasks('grunt-contrib-clean');
  grunt.loadNpmTasks('grunt-contrib-concat');
  grunt.loadNpmTasks('grunt-contrib-watch');
  grunt.registerTask('default', ['clean', 'concat', 'watch']);
};

