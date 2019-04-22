package com.insset.ccm.kevincardon.myreadingbooksback.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason="Access denied / Accès refusé")
public class ForbiddenException extends RuntimeException {
}
