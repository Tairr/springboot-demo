package com.example.service.app;



import com.example.domain.app.App;
import com.example.domain.base.BaseModel;

import java.util.List;

/**
 * Created by dell on 2017/5/16.
 * Good Luck !
 * へ　　　　　／|
 * 　　/＼7　　　 ∠＿/
 * 　 /　│　　 ／　／
 * 　│　Z ＿,＜　／　　 /`ヽ
 * 　│　　　　　ヽ　　 /　　〉
 * 　 Y　　　　　`　 /　　/
 * 　ｲ●　､　●　　⊂⊃〈　　/
 * 　()　 へ　　　　|　＼〈
 * 　　>ｰ ､_　 ィ　 │ ／／
 * 　 / へ　　 /　ﾉ＜| ＼＼
 * 　 ヽ_ﾉ　　(_／　 │／／
 * 　　7　　　　　　　|／
 * 　　＞―r￣￣`ｰ―＿
 */
public interface IAppService {
    List<App> queryForList(BaseModel baseModel);
    int addObject(BaseModel baseModel);
    int deleteObject(BaseModel baseModel);
}
