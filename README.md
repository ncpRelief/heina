# heina

a springboot web application about HEINA

## Backend

Spring boot

Run: `mvn spring-boot:run`

**APIs:**

```java
    @GetMapping("/listUsers")
    public JSONObject getUserList() {
        JSONArray users = userService.listUser();
        JSONObject result = new JSONObject();
        result.put("msg", "查询成功");
        result.put("status", 0);
        result.put("users", users);
        return result;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
```

```java
@RestController
public class HelloWorld {

    @RequestMapping("/hello")
    public String hello(){
        return  "hello world";
    }
```

#### /listUsers

desc: get users by username

GET http://127.0.0.1:8080/listUsers?userName=value&limit=value&offset=value

| param    | requeired | default | example |
|:-------- | --------- | ------- | ------- |
| userName | false     | null    | 张飞      |
| limit    | false     | 10      | 10      |
| offset   | false     | 0       | 0       |

result 

```json
{
    "users": [
        {
            "userId": "Zhangfei",
            "userName": "张飞"
        }
    ],
    "count": 1,
    "offset": 0,
    "limit": 10,
    "msg": "查询成功",
    "status": 0
}
```
#### /kfAccounts

desc: list kfaccount 

GET http://127.0.0.1:8080/kfAccounts

```json
{
    "status": 0,
    "msg": "查询客服账号成功",
    "kfAccount": [
        {
            "kfAccount": "wkeUayEAAAOcj1juBLqGSrgBLufugRhA",
            "kfName": "分诊测试非请莫入"
        },
        {
            "kfAccount": "wkeUayEAAAYAnbvcRUV_8Bwa713OWEBw",
            "kfName": "心语树洞"
        },
        {
            "kfAccount": "wkeUayEAAAwre_WgeNunupNjlnSN8osQ",
            "kfName": "树洞@NRSH"
        }
    ]
}
```

#### /refreshAccount

desc: refresh database data about kfaccount 

GET http://127.0.0.1:8080/refreshAccount

```json
{
    "status": 0,
    "msg": "刷新成功,目前共有微信客服3条"
}
```
#### /insertServer

desc: insert wechat customer server 

POST http://127.0.0.1:8080/insertServer

```json
 // 时间戳毫秒
{
  "kfAccount": "123safasdfasdf",
  "serverId":[
    {
      "startTime": 13,
      "endTime": 13,
      "users":["userid1","userId2"]
    }
  ]
}
```

#### /listServer

desc: list server users
 
Get http://127.0.0.1:8080/listServer

| param    | requeired | default | example |
|:-------- | --------- | ------- | ----- |
| kfId      | true     | -    |    wkeUayEAAAwre_WgeNunupNjlnSN8osQ   |
| limit    | false     | 10      | 10    |
| offset   | false     | 0       | 0     |

```json
 // 时间戳毫秒
{
  "kfAccount": "123safasdfasdf",
  "serverId":[
    {
      "id": "uuid",
      "startTime": 13,
      "endTime": 13,
      "users":["userid1","userId2"]
    }
  ]
}
```
#### /delServer

desc: del server users

POST http://127.0.0.1:8080/listServer


```json
 // 时间戳毫秒
{
  "kfAccount":"123asdfasfd",
  "serverId":[
    {
      "id": "uuid"
    }
  ]
}
```

## Frontend

React

Run: `npm start`