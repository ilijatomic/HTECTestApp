package com.ilijatomic.htectestapp.data.storage.bean

import io.realm.RealmObject

open class ApplicationBean(
    var lastUpdated: Long = 0
) : RealmObject()