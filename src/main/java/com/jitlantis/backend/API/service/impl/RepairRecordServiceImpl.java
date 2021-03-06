package com.jitlantis.backend.API.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jitlantis.backend.API.dao.RepairRecordDao;
import com.jitlantis.backend.API.model.RepairRecord;
import com.jitlantis.backend.API.service.RepairRecordService;
import org.springframework.stereotype.Service;

@Service
public class RepairRecordServiceImpl extends ServiceImpl<RepairRecordDao, RepairRecord> implements RepairRecordService {
}
