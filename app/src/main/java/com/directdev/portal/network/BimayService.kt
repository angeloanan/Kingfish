package com.directdev.portal.network

import com.directdev.portal.BuildConfig
import com.directdev.portal.models.*
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

/**-------------------------------------------------------------------------------------------------
 * Interface for building Retrofit service.
 *------------------------------------------------------------------------------------------------*/

interface BimayService {

    //Updated all the headers
    //Bimay somehow will cause an authentication failure if you try to login through a mobile browser
    //Turns out something is off with their captcha method when the user agent is mobile
    //So the user-agent used here is from Chrome running on macOS High Sierra

    @GET("https://binusmaya.binus.ac.id/login/index.php")
    @Headers("User-Agent:Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36")
    fun getIndexHtml(): Single<Response<ResponseBody>>

    @GET("https://binusmaya.binus.ac.id/login/index.php")
    @Headers("User-Agent:Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36")
    fun getIndexHtmlToken(@Header("Cookie") cookie: String = ""): Single<Response<ResponseBody>>

    @FormUrlEncoded
    @Headers("Referer: https://binusmaya.binus.ac.id/login/",
            "Origin: https://binusmaya.binus.ac.id",
            "User-Agent:Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36")
    @POST("https://binusmaya.binus.ac.id/login/sys_login.php")
    fun signIn2(@Header("Cookie") cookie: String,
                @FieldMap fields: Map<String, String>)
            : Single<Response<String>>

    @Headers("Referer: https://binusmaya.binus.ac.id/newStaff/",
            "Origin: https://binusmaya.binus.ac.id",
            "User-Agent:Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36")
    @POST("https://binusmaya.binus.ac.id/services/ci/index.php/login/switchrole/2/104")
    fun switchRole(@Header("Cookie") cookie: String): Single<ResponseBody>

    @Headers("Referer: https://binusmaya.binus.ac.id/newStudent/",
            "User-Agent: Portal/" + BuildConfig.VERSION_NAME)
    @GET("student/profile/profileStudent")
    fun getProfile(@Header("Cookie") cookie: String): Single<ResponseBody>

    @Headers("Referer: https://binusmaya.binus.ac.id/newStudent/",
            "User-Agent:Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36")
    @GET("financial/virtualaccount/")
    fun getFinanceSummary(@Header("Cookie") cookie: String): Single<ResponseBody>

    @Headers("Referer: https://binusmaya.binus.ac.id/newStudent/",
            "User-Agent:Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36")
    @GET("financial/getFinancialSummary")
    fun getFinances(@Header("Cookie") cookie: String): Single<List<FinanceModel>>

    @Headers("Referer: https://binusmaya.binus.ac.id/newStudent/index.html",
            "User-Agent:Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36")
    @GET("student/class_schedule/classScheduleGetStudentClassSchedule")
    fun getSessions(@Header("Cookie") cookie: String): Single<List<SessionModel>>

    @Headers("Referer: https://binusmaya.binus.ac.id/newstudent/",
            "User-Agent:Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36")
    @POST("newExam/Schedule/getOwnScheduleStudent")
    fun getExams(@Header("Cookie") cookie: String, @Body body: ExamRequestBody): Single<List<ExamModel>>

    @Headers("Referer: https://binusmaya.binus.ac.id/newstudent/",
            "User-Agent:Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36")
    @POST("scoring/ViewGrade/getStudentScore/{term}")
    fun getGrades(@Path("term") term: String, @Header("Cookie") cookie: String): Single<GradeModel>

    @Headers("Referer: https://binusmaya.binus.ac.id/newstudent/",
            "User-Agent:Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36")
    @POST("https://binusmaya.binus.ac.id/services/ci/index.php/scoring/ViewGrade/getPeriodByBinusianId")
    fun getTerms(@Header("Cookie") cookie: String): Single<List<TermModel>>

    @Headers("Referer: https://binusmaya.binus.ac.id/newstudent/",
            "User-Agent:Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36")
    @GET("student/init/getCoursesBySTRMAndAcad/{term}")
    fun getCourse(@Path("term") term: String, @Header("Cookie") cookie: String): Single<CourseWrapperModel>

    @Headers("Referer: https://binusmaya.binus.ac.id/newstudent/",
            "User-Agent:Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36")
    @GET("student/classes/resources/{courseId}/{crseId}/{term}/{ssrComponent}/{classNumber}")
    fun getResources(@Path("courseId") courseId: String,
                     @Path("crseId") crseId: String,
                     @Path("term") term: String,
                     @Path("ssrComponent") ssrComponent: String,
                     @Path("classNumber") classNumber: String,
                     @Header("Cookie") cookie: String): Single<ResModelIntermidiary>

    @Headers("Referer: https://binusmaya.binus.ac.id/newstudent/",
            "User-Agent:Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36")
    @GET("student/classes/assignmentType/{courseId}/{crseId}/{term}/{ssrComponent}/{classNumber}/01")
    fun getAssignment(@Path("courseId") courseId: String,
                      @Path("crseId") crseId: String,
                      @Path("term") term: String,
                      @Path("ssrComponent") ssrComponent: String,
                      @Path("classNumber") classNumber: String,
                      @Header("Cookie") cookie: String): Single<List<AssignmentIndividualModel>>

    @Headers("User-Agent:Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36")
    @GET("https://binusmaya.binus.ac.id/login/loader.php")
    fun getSerial(@Header("Cookie") cookie: String,
                  @Query(value = "serial") serial: String,
                  @Header("Referer") referrer: String): Single<Response<ResponseBody>>
}
