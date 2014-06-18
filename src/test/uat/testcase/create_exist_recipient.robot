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
    Then verify create recipient  添加用户失败

create new recipient
    Given make sure username and address is new
    When create recipient
    Then verify create recipient  添加用户成功

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

verify create recipient
    [Arguments]  ${expectContent}
    sleep   2s
    page should contain  ${expectContent}

make sure username and address is new
    Connect To Database Using Custom Params      cymysql    db='oms',user='root',passwd='root', host='localhost',port=3306
    Execute Sql String    delete from recipients where email='zhaozhiming003@gmail.com'
    Disconnect from database
