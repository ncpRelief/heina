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

## Frontend

React

Run: `npm start`