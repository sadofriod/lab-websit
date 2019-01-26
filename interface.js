//  user data interface


//  sign in return data struct
// insterface name:  /signIn
const userData = {
    code: 0, //0 成功 1 用户不存在 2 用户名或密码错误
    mesage: 'success', // 如果成功 返回success 不成功返回失败原因
    data: {
        username: 'test', //string 类型，
        userID: 0, //整型 这个值由后端维护 是用户在数据库中的唯一标识
        isNormal: true, //是否是普通用户boolean
        isMumber: false, //是否是成员 boolean
        isAdministor: false, // 是否是管理员
        token: 'iquewrj12309lk' // 一段由后台生成的 可以用来记录用户登陆状态和时间的值 将会存入cookie中
    }
}

// sign up upload data struct.
// 下面是注册功能  给后端传入参数 在传输时 会使用到 JSON.stringify() 函数将其转换成json 字符串
// insterface name:  /signUp
const userReigter = {
    username: '', //8~16位长 没有字符类型限制
    account: '', //8~16位长
    password: '',//8~32位长
    isNormal: true, //是否是普通用户boolean
    isMumber: false, //是否是成员 boolean 提交后 由管理员进行审批
    isAdministor: false, // 是否是管理员 提交后 由管理员进行审批
    mumberInfo:{ //当 isMumber 是true 的时候 为必填
        mumbername:'',
        major:'',// 专业
        description:''//自我介绍 包括掌握的知识
    },
    administorInfo:{
        adminName:'',
        call_number:''
    }
}

// insterface name:  /signUp
// sign up return data struct

const userInfo = {
    code: 0, //0 成功 1 用户不存在 2 用户名或密码错误
    mesage: 'success', // 如果成功 返回success 不成功返回失败原因
}

// insterface name: /getNavigators

