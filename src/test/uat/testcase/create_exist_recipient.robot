*** Settings ***
Library    Selenium2Library
Library    DatabaseLibrary
Resource   resource.robot
Test Setup      Open Browser  ${baseurl}/#/recipients
Test Teardown   Close Browser

*** TestCases ***
create exist recipient
    Given username and address is existed
    When create recipient
    Then create recipient fail

create new recipient
    Given make sure username and address is new
    When create recipient
    Then create recipient success

*** Keywords ***
username and address is existed
    Connect To Database Using Custom Params      cymysql    db='oms',user='root',passwd='root', host='localhost',port=3306
    Execute Sql String    delete from recipients where email='zhaozhiming003@gmail.com'
    Execute Sql String    insert into recipients(username,email) values('zhaozhiming','zhaozhiming003@gmail.com')
    Disconnect from database

create recipient
    Input text    username    zhaozhiming
    Input text    email    zhaozhiming003@gmail.com
    click element  css=.submit

create recipient fail
    sleep   2s
    page should contain  添加用户失败

make sure username and address is new
    Connect To Database Using Custom Params      cymysql    db='oms',user='root',passwd='root', host='localhost',port=3306
    Execute Sql String    delete from recipients where email='zhaozhiming003@gmail.com'
    Disconnect from database

create recipient success
    sleep   2s
    page should contain  添加用户成功