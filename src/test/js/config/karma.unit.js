basePath = '../../../../';

files = [
    JASMINE,
    JASMINE_ADAPTER,
    './src/test/js/lib/jquery.js',
    './src/test/js/lib/semantic.js',
    './src/test/js/lib/angular.js',
    './src/test/js/lib/angular-route.js',
    './src/test/js/lib/angular-mocks.js',
    './src/main/webapp/resources/js/recipients.js',
    './src/main/webapp/resources/js/index.js',
    './src/main/webapp/resources/js/send.js',
    './src/test/js/unit/**/*.js'
];

browsers = ['Chrome'];
reporters = ['progress', 'growl'];

autoWatch = true;
singleRun = false;