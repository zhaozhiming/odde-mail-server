basePath = '../../../../';

files = [
    JASMINE,
    JASMINE_ADAPTER,
    './src/main/webapp/resources/js/recipients.js',
    './src/test/js/lib/**/*.js',
    './src/test/js/unit/**/*.js'
];

browsers = ['Chrome'];
reporters = ['progress', 'growl'];

autoWatch = true;
singleRun = false;