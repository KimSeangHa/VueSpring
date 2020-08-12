import Vue from 'vue'
import Vuex from 'vuex'

import test from '@/store/modules/test'

Vue.use(Vuex)

/**
 * store 모듈 관리 방법
 * 1. @/store/modules/module_name 해당 위치에 모듈 작성
 * 2. import module
 *    ex) import module_name from '@/store/modules/module_name'
 * 3. 하단 modules 객체에 프로퍼티 추가
 */
export default new Vuex.Store({
  /**
   * component의 data와 유사한 역할을 합니다.
   * 컴포넌트 내에서 참조방법
   * 1. this.$store.state.xxx
   * 2. computed: { ...mapState(['xxx']) }
   */
  state: {
  },

  /**
   * component의 computed와 유사한 역할을 합니다.
   * 컴포넌트 내에서 참조방법
   * computed: { ...mapGetters(['todosCount']) }
   */
  getters: {
  },

  /**
   * component의 methods와 유사한 역할을 합니다.
   * state를 변경할 때 사용합니다.
   * this.$store.commit('setTodos', [payload])
   */
  mutations: {},

  /**
   * component의 method와 유사한 역할을 합니다.
   * 비동기 처리를 합니다.
   * this.$store.dispatch('getTodos, [payload])
   */
  actions: {},

  /**
   * 모듈 관리 영역
   */
  modules: {
    test
  }
})
