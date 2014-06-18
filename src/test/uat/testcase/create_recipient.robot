*** Settings ***
Library    Selenium2Library
Library    DatabaseLibrary
Resource   resource.robot
Test Setup      Open Browser  ${baseurl}/#/recipients
Test Teardown   Close Browser

*** TestCases ***
create new recipient
    Given make sure recipient is new
    When create recipient
    Then verify create recipient  添加用户成功

create exist recipient
    Given make sure recipient is new
    Given create new recipient by sql
    When create recipient
    Then verify create recipient  添加用户失败

*** Keywords ***
create new recipient by sql
    Connect To Database Using Custom Params      cymysql    db='${dbname}',user='${dbuser}',passwd='${dbpassword}', host='${dbhost}',port=${dbport}
    Execute Sql String    insert into recipients(username,email) values('zhaozhiming','zhaozhiming003@gmail.com')
    Disconnect from database

create recipient
    Input text    username    zhaozhiming
    Input text    email    zhaozhiming003@gmail.com
    click element  css=.submit

verify create recipient
    [Arguments]  ${expectContent}
    sleep   2s
    page should contain  ${expectContent}

make sure recipient is new
    Connect To Database Using Custom Params      cymysql    db='${dbname}',user='${dbuser}',passwd='${dbpassword}', host='${dbhost}',port=${dbport}
    Execute Sql String    delete from recipients where email='zhaozhiming003@gmail.com'
    Disconnect from database
