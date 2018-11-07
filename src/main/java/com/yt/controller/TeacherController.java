package com.yt.controller;


import com.yt.domain.Od;
import com.yt.domain.Teacher;
import com.yt.domain.TeacherTime;
import com.yt.domain.User;
import com.yt.repository.OdRepository;
import com.yt.repository.TeacherRepository;
import com.yt.repository.TeacherTimeRepository;
import com.yt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpSession;
import javax.xml.ws.soap.Addressing;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.yt.config.WebSecurityConfig.SESSION_KEY;

@Controller
public class TeacherController {

    @Autowired
    OdRepository odRepository;
    @Autowired
    TeacherTimeRepository teacherTimeRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TeacherRepository teacherRepository;

    @RequestMapping("/finishOd")
    public @ResponseBody Map<String,Object> finishOd(@SessionAttribute(SESSION_KEY)String uid,String time){
        Map<String,Object> map = new HashMap<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm");
        Od od = null;
        try {
            od = odRepository.findByTeacherTime_Id(teacherTimeRepository.findByAnswerTime(simpleDateFormat.parse(time)).getId());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        od.setState(2);
        odRepository.save(od);
        map.put("success",true);
        map.put("message","答疑完成");
        return map;

    }

    @RequestMapping("/agreeOd")
    public @ResponseBody Map<String,Object> agreeOd(@SessionAttribute(SESSION_KEY)String uid,String time,String place){
        Map<String ,Object> map = new HashMap<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm");
        TeacherTime teacherTime = null;
        try {
            teacherTime = teacherTimeRepository.findByAnswerTime(simpleDateFormat.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Od od = odRepository.findByTeacherTime_IdAndState(teacherTime.getId(),0);
        od.setState(1);
        od.setAnswerPlace(place);
        odRepository.save(od);
        map.put("success",true);
        map.put("message","成功回复预约");
        return map;
    }

    @RequestMapping("/delTime")
    public @ResponseBody Map<String,Object> delTime(@SessionAttribute(SESSION_KEY) String uid,String time){
        Map<String ,Object> map = new HashMap<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm");
        TeacherTime teacherTime=null;
        try {
            teacherTime = teacherTimeRepository.findByTeacher_NameAndAnswerTime(uid,simpleDateFormat.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Od od = odRepository.findByTeacherTime_Id(teacherTime.getId());
        teacherTimeRepository.delete(teacherTime);
        if(od!=null){
            odRepository.delete(od);
        }
        map.put("success",true);
        map.put("message","删除预约成功");
        return map;
    }

    @RequestMapping("/addTime")
    public @ResponseBody Map<String,Object> addTime(@SessionAttribute(SESSION_KEY) String uid,String time){
        Map<String,Object> map = new HashMap<>();
        TeacherTime teacherTime = new TeacherTime();
        teacherTime.setState(0);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm");
        System.out.println(time);
        if(time==null){
            map.put("success",false);
            map.put("message","信息有误请从新添加");
            return map;
        }
        try {
            teacherTime.setAnswerTime(simpleDateFormat.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(time.toString());
        teacherTime.setTeacher(teacherRepository.findByName(uid));

        teacherTimeRepository.save(teacherTime);
        map.put("success",true);
        return map;
    }

    @GetMapping("/OnlineTeaching/StudentMaterial")
    public String olStuMa(@SessionAttribute(SESSION_KEY)String uid, Model model){
        List<TeacherTime> teacherTimeslist = teacherTimeRepository.findByTeacher_Name(uid);
        List<String> teacherTimes = new ArrayList<>();
        List<Integer> states = new ArrayList<>();
        for (TeacherTime teacherTime : teacherTimeslist) {

            String x = teacherTime.getAnswerTime().toString();System.out.println(x);
            teacherTimes.add(x);
            states.add(teacherTime.getState());
        }
        model.addAttribute("teacherTimes",teacherTimes);
        model.addAttribute("states",states);
        return "/OnlineTeaching/StudentMaterial";
    }

    @GetMapping("/OnlineTeaching/StudentStudyRecordList")
    public String olStuRec(@SessionAttribute(SESSION_KEY)String uid,Model model){

        List<TeacherTime> teacherTimes = teacherTimeRepository.findByTeacher_Name(uid);
        List<Od> odList = new ArrayList<>();
        for (TeacherTime teacherTime : teacherTimes) {
            Od od = new Od();
            od = odRepository.findByTeacherTime_IdAndState(teacherTime.getId(),0);
            if(od!=null)
                odList.add(od);
        }
        System.out.println(odList.size());
        List<String> studentName = new ArrayList<>();
        List<String> teacherTimess = new ArrayList<>();
        for (Od od : odList) {

            studentName.add(od.getStudent().getName());
            teacherTimess.add(od.getTeacherTime().getAnswerTime().toString());
        }
        model.addAttribute("studentName",studentName);
        model.addAttribute("teacherTime",teacherTimess);
        return "/OnlineTeaching/StudentStudyRecordList";
    }

    @GetMapping("/OnlineTeaching/OrderFinish")
    public String OdFinish(@SessionAttribute(SESSION_KEY)String uid,Model model){
        Teacher teacher = teacherRepository.findByName(uid);
        List<TeacherTime> teacherTimes = teacherTimeRepository.findByTeacher_Name(uid);
        List<Od> odList = new ArrayList<>();
        for (TeacherTime teacherTime : teacherTimes) {
            Od od = new Od();
            od = odRepository.findByTeacherTime_IdAndState(teacherTime.getId(),1);
            if(od==null){
                od = odRepository.findByTeacherTime_IdAndState(teacherTime.getId(),2);
            }
            if(od==null)
                continue;
            odList.add(od);
        }
        List<String> teacherTime = new ArrayList<>();
        List<String> studentName = new ArrayList<>();
        List<String> place = new ArrayList<>();
        List<Integer> states = new ArrayList<>();
        for (Od od : odList) {
            teacherTime.add(od.getTeacherTime().getAnswerTime().toString());
            studentName.add(od.getStudent().getName());
            place.add(od.getAnswerPlace());
            states.add(od.getState());
        }
        model.addAttribute("teacherTime",teacherTime);
        model.addAttribute("studentName",studentName);
        model.addAttribute("place",place);
        model.addAttribute("states",states);
        return "/OnlineTeaching/OrderFinish";
    }





}
