package com.ppwei.myhr.service;

import com.ppwei.myhr.bean.Hr;
import com.ppwei.myhr.common.HrUtils;
import com.ppwei.myhr.mapper.HrMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HrService implements UserDetailsService {

    @Autowired
    HrMapper hrMapper;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Hr hr = hrMapper.loadUserByUsername(s);
        if(hr == null){
            throw new UsernameNotFoundException("用户名不对");
        }
        return hr;
    }

    public List<Hr> getAllHr() {
        return hrMapper.getAllHr(null);
    }

    public List<Hr> getAllHrExceptAdmin() {
        return hrMapper.getAllHr(HrUtils.getCurrentHr().getId());
    }
}
