package com.mycompany.runfun.service;

import com.mycompany.runfun.domain.Record;

public interface RecordService {

	Record findByUsernameAndId(String username, Long id);
}
