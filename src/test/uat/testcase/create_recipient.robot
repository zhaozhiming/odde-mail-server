*** Settings ***
Library    Selenium2Library
Library    DatabaseLibrary
Resource   resource.robot
Test Setup      Open Browser  ${baseurl}/#/recipients  ${browser}
Test Teardown   Close Browser

*** TestCases ***
create new recipient
    Given 执行sql  delete from recipients where email='zhaozhiming003@gmail.com'
    When create recipient
    Then verify create recipient  添加用户成功

create exist recipient
    Given 执行sql  delete from recipients where email='zhaozhiming003@gmail.com'
    Given 执行sql  insert into recipients(username,email) values('zhaozhiming','zhaozhiming003@gmail.com')
    When create recipient
    Then verify create recipient  添加用户失败

*** Keywords ***
执行sql
    [Arguments]  ${sql}
    Connect To Database Using Custom Params      cymysql    db='${dbname}',user='${dbuser}',passwd='${dbpassword}', host='${dbhost}',port=${dbport}
    Execute Sql String    ${sql}
    Disconnect from database

create recipient
    Input text    username    zhaozhiming
    Input text    email    zhaozhiming003@gmail.com
    click element  css=.submit

verify create recipient
    [Arguments]  ${expectContent}
    sleep   2s
    page should contain  ${expectContent}
