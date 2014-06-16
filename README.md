# Odde Mail Server

### [Todo List][url1]

[![Build Status](https://buildhive.cloudbees.com/job/zhaozhiming/job/odde-mail-server/badge/icon)](https://buildhive.cloudbees.com/job/zhaozhiming/job/odde-mail-server/)

### JavaScript tests

The JavaScript tests use [Karma Runner][karma]. Rather than rely on a
global install of Karma, this project uses a local install.

Run `npm install` to install all the local JS dependencies and then
run:

  node_modules/karma/bin/karma start src/test/js/config/karma.unit.js

This will start the live Karma test runner.

For convenience use the supplied Rake task: `rake test:karma:start`.


[url1]: https://github.com/zhaozhiming/odde-mail-server/blob/master/docs/todo.md
[karma]: http://karma-runner.github.com/
