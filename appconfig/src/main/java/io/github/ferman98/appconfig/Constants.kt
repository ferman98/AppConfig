package io.github.ferman98.appconfig

object Constants {
    interface DataAppConfig {
        companion object {
            var activityName: String = ""
            var activityIntentExtras: Map<String, String> = mapOf()
            var activityVariable: Map<String, String> = mapOf()
            var fragmentName: String = ""
            var fragmentVariable: Map<String, String> = mapOf()
        }
    }
}