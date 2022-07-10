package com.bobo.course

import androidx.lifecycle.ViewModel
import com.bobo.common.base.BaseViewModel
import com.bobo.course.net.CourseDetails
import com.bobo.course.repo.ICourseResource

class PlayVideoViewModel(val service: ICourseResource): BaseViewModel() {

    val liveCourseDetails = service.liveCourseDetails

    fun getCourseDetails(course_id: Int) = serverAwait {
        service.getCourseDetails(course_id)
    }

    var arrayLiveCourseDetails = ArrayList<CourseDetails.CourseDetailsItem?>()

}