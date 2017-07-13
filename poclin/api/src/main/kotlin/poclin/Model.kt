package swatlin

// C O M M O N /////////////////////////////////////////////////////////////////////////////////////
// Entities common to all models

data class Id(val time: Long, val place: Long)

data class Alias(val id: Id, val code: String) {
    companion object {
        const val CODE_REGEX = "[a-z_][a-z_0-9]*"
    }

    init {
        require(code.matches(CODE_REGEX.toRegex()))
    }
}

/** I18N Text. Check azampar */
data class Text(val code: String)

/** Some tags can be related. Ie: backlog, working, done -> status */
data class Tag(val id: Id, val name: Text)
