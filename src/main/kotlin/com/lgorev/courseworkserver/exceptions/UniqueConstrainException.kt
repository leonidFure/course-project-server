package com.lgorev.courseworkserver.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class UniqueConstrainException(message: String?) : RuntimeException(message)