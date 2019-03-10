package com.gusuran.authentication.service.impl;

import com.gusuran.authentication.repository.api.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserServiceImplTest {

  @InjectMocks
  private UserServiceImpl service;

  @Mock
  private UserRepository userRepository;

  @Before
  public void setUp() throws Exception {
    initMocks(this);
  }

  @After
  public void tearDown() throws Exception {
    verifyNoMoreInteractions(userRepository);
  }
}
