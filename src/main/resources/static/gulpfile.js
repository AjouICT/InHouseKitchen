var gulp        = require('gulp');
var sass = require('gulp-sass');
var concat = require('gulp-concat');
var minify      = require('gulp-minify-css');
var rename      = require('gulp-rename');
var uglify      = require('gulp-uglify');
var plumber     = require('gulp-plumber');

// Variables
var distDir = './dist';
var pkg = require('./package.json');



// Clean
gulp.task('clean', function() {
  del.sync([distDir]);
});


// Javascript
gulp.task('js', function() {
  gulp.src(
		   [
   './assets/plugins/**/js/*.js',   
  ]) 
   .pipe(concat('plugins.min.js'))
  // .pipe(uglify())
    .pipe(gulp.dest(distDir + '/js'));
});

// Javascript
gulp.task('revjs', function() {
  gulp.src(
		   [
   './assets/plugins/revolution/js/extensions/*.js',   
  ]) 
   .pipe(concat('revslider.min.js'))
  // .pipe(uglify())
    .pipe(gulp.dest(distDir + '/js'));
});

// CSS
gulp.task('css', function() {
   gulp.src(['./assets/plugins/**/css/*.css'])   
    .pipe(concat('plugins.min.css'))
    // Original
    .pipe(gulp.dest(distDir + '/css'))
    // Minified
    .pipe(minify())    
    .pipe(gulp.dest(distDir + '/css'));
});

// sass
gulp.task('sass', function() {
  return gulp.src(['./assets/css/variables.scss',
  './assets/css/common.scss',
'./assets/css/header.scss',
   './assets/css/style.scss',
  './assets/css/footer.scss',
   './assets/css/responsive.scss', 
  ])  
  .pipe(plumber())
  .pipe(concat('main.min.scss'))
    .pipe(sass(distDir.sassSrcPath, {
            style: 'compressed',
            loadPath: [distDir.sassImportsPath]
        }))    
    // Original
    .pipe(gulp.dest(distDir + '/css'))
    // Minified
  //  .pipe(minify())    
    .pipe(gulp.dest(distDir + '/css'));
});




// Watch
gulp.task('watch', ['js', 'revjs','css', 'sass'], function() {
  gulp.watch('./assets/plugins/**/*', ['js']);
  gulp.watch('./assets/plugins/**/**/*', ['revjs']);
  gulp.watch('./assets/plugins/**/*', ['css']);
   gulp.watch('./assets/css/**/*', ['sass']);
});


// Defaults
gulp.task('build', ['js', 'css', 'sass','revjs']);
gulp.task('default', ['build']);

