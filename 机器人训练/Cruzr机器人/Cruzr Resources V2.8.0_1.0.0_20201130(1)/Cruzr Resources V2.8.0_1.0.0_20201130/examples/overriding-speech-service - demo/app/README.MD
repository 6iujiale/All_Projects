# 语音服务引擎替换说明

ROSA 开放覆盖语音系统服务，支持按模块替换系统上的语音识别 (ASR)、自然语言理解 (NLP)以及语音合成 (TTS) 3 个模块的默认实现。具体替换步骤如下 :

### 权限配置

```xml
<!--  使用 Rosa 必要的条件 -->
<uses-permission android:name="com.ubtrobot.permission.ROBOT" />
```

### 添加库依赖

实现语音服务的覆盖，需要添加两个必须的依赖库，`rosa-x.y.z.jar` 和 `rosa-speech-override-1.0.0.aar` ，依赖包请在官网下载或者联系供应商获取。

在 `app/build.gradle` 添加以下代码。

```groovy
android  {
    repositories {
        flatDir {
            dirs 'rosa-libs'
        }
    }
}
```

然后将 `rosa-x.y.z.jar` 和 `rosa-speech-override-1.0.0.aar` 放置于 `app/rosa-libs` 目录中，并在 `app/build.gradle` 文件的 dependencies 块内添加依赖。

```groovy
dependencies {
        compileOnly files('rosa-libs/rosa-2.7.0.jar')
        implementation(name: 'rosa-speech-override-1.0.0', ext: 'aar')
}
```

> ```
> 注意： 
> 1. 如果系统固件中已经内置了相应的 jar 文件，请使用 compileOnly 方式，    
> 	表示仅编译使用，并且在 manifest 文件中，添加以下配置             
> 	<uses-library            
> 		android:name="com.ubtrobot.rosa"            
> 		android:required="true" /> 
> 2. 如果系统固件中未内置相应的 jar ,请使用 implementation 方式
> ```

### 配置覆盖规则

要使覆盖生效，还需要进行适当的配置。在 `app/src/main/res/xml` 目录中创建 `decision_list.xml` 文件，添加覆盖配置。指定在系统语言切换至某个语言时，覆盖默认的语音服务。

```xml
<?xml version="1.0" encoding="utf-8"?>
<decision-list>
    <decision>
        <if>
            <!-- 选择在什么语言下替换语言服务 -->
            <language name="en-rUS" />
        </if>
        <then>
            <route-service>
                  <!--
                    注意：如果不需要替换请注释掉相应的模块
                    package: "你的应用包名"
                    service: 无需修改 />
                    -->
                <!-- 如果覆盖语音识别 -->
                <parameter-group
                    package="你的应用包名"
                    service="speech.recognition" />
                <!-- 如果覆盖语音合成 -->
                <parameter-group
                    package="你的应用包名"
                    service="speech.synthesis" />
                <!-- 如果覆盖语义理解 -->
                <parameter-group
                    package="你的应用包名"
                    service="speech.understanding" />
            </route-service>
        </then>
    </decision>
</decision-list>
```

### 实现你的服务

**注：以下覆盖的所有 Java 方法均执行在主线程上。耗时任务请在非主线程上执行**

1. ##### 语音识别服务（ASR）


~~~java
public class YourRecognitionService extends AbstractRecognizer {

    @Override
    protected void startRecognizing(RecognitionOption recognitionOption) {
        /** recognitionOption 参数说明:
         *      mode:识别模式，default:MODE_SINGLE
         *          1) MODE_SINGLE:单次识别;
         *          2) MODE_CONTINUOUS:连续识别;
         *          特别注意:
         *              若模式为 MODE_SINGLE, 识别结果应使用 resolveRecognizing 方法通知调用者识别结果,
         *              若模式为 MODE_CONTINUOUS, 识别结果应使用 reportRecognizingProgress 方法将结果通知给调用者.
         *      distanceRange:识别范围,可选;
         *      extension:扩展参数,可选;
         *      understandingOption:语义理解参数,可选(部分引擎同时包含识别和语义理解);
         */

        //TODO 你应该在此处添加识别的具体实现代码
        //your function code...
        
        //TODO 识别进度处理
        /** 1. 识别过程:如果你需要把识别过程通知给调用者,你可以调用该方法,并且你还可以在通知时携带一些信息.
        注意： 该方法与 startRecognizing() 对应，可多次执行
        持续识别模式下应使用此接口上报识别结果*/
        reportRecognizingProgress(
                new RecognitionProgress.Builder(RecognitionProgress.PROGRESS_BEGAN) //进度类型:
                        // 1) PROGRESS_BEGAN: 识别开始;
                        // 2) PROGRESS_RECOGNIZING:识别中;
                        // 3) PROGRESS_ENDED:识别结束;
                        // 4) PROGRESS_RECOGNITION_TEXT_RESULT:识别结果;
                        // 5) PROGRESS_UNDERSTANDING_RESULT:语义理解结果;
                        .setTextResult("RecognitionResult")  //识别结果,可选(MODE_CONTINUOUS 时,应填写识别结果在此处);
                        .setDecibel(50)                      //识别到的声音大小,可选;
                        .setUnderstandingResult(null)        //语义理解结果,可选(部分引擎同时包含识别和语义理解);
                        .build());

        //TODO 识别结果处理
        /** 2. 识别结束:当识别完成时,你可以调用该方法通知识别结果给调用者,.
         注意： 该方法与 startRecognizing() 对应，只生效一次，
         持续识别模式下如果使用此方法,本次识别将会结束*/
        resolveRecognizing(new RecognitionResult
                .Builder("RecognitionResult")              //识别结果（单次识别模式下必填）
                .setUnderstandingResult(null)              //语义理解结果,可选(部分引擎同时包含识别和语义理解);
                .build());

        //TODO 识别异常处理
        /** 3. 识别异常:当识别发生错误时,你可以调用该方法通知调用者,该方法只生效一次.
         注意： 该方法与 startRecognizing() 对应，只生效一次，
         持续识别模式下如果使用此方法,本次识别将会结束*/
        rejectRecognizing(new RecognitionException(
                RecognitionException.CODE_INTERNAL_ERROR,   //错误码,使用给定的
                123,                                        //定义自己的错误码,可选
                "Error Message")                            //错误信息,可选
        );
    }

    @Override
    protected void stopRecognizing() {
        //TODO 你应该在此处停止识别
        //your function code...
    }
}
~~~

2. ##### 语义理解服务(NLP)



~~~java
public class YourUnderstandingService extends AbstractUnderstander {

    @Override
    protected void understand(UnderstandingOption understandingOption, Deferred<UnderstandingResult, UnderstandingException> deferred) {
        /** understandingOption 参数说明：
         *      InputText:输入的文本;
         *      SessionId:会话ID可选,可选;
         *      ContextList:上下文列表,可选;
         *      Parameters:参数集合,可选;
         *      TimeoutMillis:超时时间,可选;
         *      Version:版本,可选;
         */

        //TODO 你应该在此处添加语义理解的具体实现代码
        //your function code...

        //TODO 1. 语义理解完成处理，该方法与 understand() 对应，只生效一次
        deferred.resolve(new UnderstandingResult
                .Builder()
                .setFulfillmentList(null)       //语义理解返回的json内容,可选
                .setSpeechFulfillment(null)     //语义理解返回的数据,可选，TTS 默认播报该处内容
                .setLanguage("en-rUS")           //语言,可选
                .setIntent(null)                //语义理解返回的意图，分发skll时使用,可选
                .setSessionId("")               //SessionId,可选 (see UnderstandingOption.SessionId)
                .setSessionIncomplete(false)    //多轮对话结束标志,可选
                .setInputText("")               //输入的文本,可选 (see UnderstandingOption.InputText)
                .setContextList(null)           //上下文列表,可选  (see UnderstandingOption.ContextList)
                .setVersion("")                 //版本,可选 (see UnderstandingOption.Version)
                .setSource("")                  //平台,可选
                .build());

        //TODO 2. 语义理解异常处理，该方法与 understand() 对应，只生效一次
        deferred.reject(new UnderstandingException(
                UnderstandingException.CODE_INTERNAL_ERROR,     //错误码,使用给定的
                123,                                            //定义自己的错误码,可选
                "Error Message")                                //错误信息,可选
        );
    }
}
~~~



3. ##### 语音合成服务(TTS)

~~~java
public class YourSynthesisService extends AbstractSynthesizer {

    @Override
    protected void startSynthesizing(SynthesisOption synthesisOption) {
        /** synthesisOption 参数说明：
         *      inputText:合成内容;
         *      speakingVoiceId:发音人;
         *      speakingSpeed: 速度 0.1~1~10, default:1;
         *      speakingVolume: 音量 0~100，default:0;
         */

        //TODO 你应该在此处添加语音合成的具体实现代码
        //your function code...

        //TODO 1. 合成过程通知，该方法与 startSynthesizing() 对应，可多次执行
        reportSynthesizingProgress(new SynthesisProgress
                .Builder(SynthesisProgress.PROGRESS_BEGAN)
                //进度通知类型
                // 1) PROGRESS_BEGAN 播报开始
                // 2) PROGRESS_PLAYING 播报中
                // 3) PROGRESS_ENDED 播报结束
                .setAudioBytes(new byte[]{})             //音频数据,可选
                .setPlayProgress(10)                     //进度百分比,可选
                .setRemainingTimeMillis(10 * 1000)       //剩余时间,可选
                .build());

        //TODO 2. 合成结束处理，该方法与 startSynthesizing() 对应，只生效一次
        resolveSynthesizing();

        //TODO 3. 合成异常处理，该方法与 startSynthesizing() 对应，只生效一次
        rejectSynthesizing(new SynthesisException(
                SynthesisException.CODE_INTERNAL_ERROR,  //错误码,使用给定的
                123,                                     //定义自己的错误码,可选
                "Error Message"));                       //错误信息,可选
    }

    @Override
    protected void stopSynthesizing() {
        //TODO 你应该在此处停止语音合成
        //your function code...
    }

    @Override
    public List<SpeakingVoice> getSpeakingVoiceList() {
        //TODO 如果你拥有多个发音人,你应该在此给出支持的发音人列表
        //your SpeakingVoiceList
        SpeakingVoice speakingVoice = new SpeakingVoice.Builder("1234") //发音人ID
                .setName("jack")										//发音人名称
                .setGender(0)											//发音人性别
                .setLanguage("en-rUS")									//发音人对应语种
                .build();
        return Collections.singletonList(speakingVoice);
    }
}
~~~

### 声明模块

想要让你自己实现的服务生效，你必须对其进行声明

```java
//TODO 初始化
ServiceModules.initialize(this);

//声明你的语音识别服务
ServiceModules.declare(
    Recognizer.class,
    new ModuleCreator<Recognizer>() {
        @Override
        public void createModule(Class<Recognizer> aClass,
                                 ModuleCreatedNotifier<Recognizer> notifier) {
            notifier.notifyModuleCreated(new YourRecognitionService());
        }
    }
);

//声明你的语音合成服务
ServiceModules.declare(
    Synthesizer.class,
    new ModuleCreator<Synthesizer>() {
        @Override
        public void createModule(Class<Synthesizer> aClass,
                                 ModuleCreatedNotifier<Synthesizer> notifier) {
            notifier.notifyModuleCreated(new YourSynthesisService());
        }
    }
);

//声明你的语音理解服务
ServiceModules.declare(
    Understander.class,
    new ModuleCreator<Understander>() {
        @Override
        public void createModule(Class<Understander> aClass,
                                 ModuleCreatedNotifier<Understander> notifier) {
            notifier.notifyModuleCreated(new YourUnderstandingService());
        }
    }
);
```

### 注意事项

1. 首次安装时，设备需要重启，覆盖才能生效。
2. 同一个系统中，请避免同时安装2个用于覆盖语音的 app
3. 不同的产品有可能会有其特殊设置，出现问题时，请与相关产品负责人联系
4. 示例代码仅实现较为基础通用的功能