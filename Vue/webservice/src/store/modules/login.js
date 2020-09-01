import axios from 'axios'
import router from '@/router/index'

export default {
    /**
     * 하기 항목들에 namespace를 사용합니다.
     * [getters, actions, mutaions]
     */
    namespaced: true,
  
    /**
     * component의 data와 유사한 역할을 합니다.
     * 컴포넌트 내에서 참조방법
     * 1. this.$store.state.sample.todos
     * 2. computed: { ...mapState('sample', ['todos']) }
     */
    state: {
      loginResult: '',
      loginToken: ''
    },
  
    /**
     * component의 computed와 유사한 역할을 합니다.
     * 컴포넌트 내에서 참조방법
     * computed: { ...mapGetters('sample', ['todosCount']) }
     */
    getters: {
      getLoginResult: state => {
        return state.loginResult
      }
    },
  
    /**
     * component의 methods와 유사한 역할을 합니다.
     * state를 변경할 때 사용합니다.
     * this.$store.commit('sample/setTodos', [payload])
     */
    mutations: {
    /**
     * 로그인
     *
     * @param {object} state
     * @param {array} payload
     */
    Login (state, payload) {
      console.log('## Login ##')
      axios.post('http://localhost:9090/webservice/Login', { 
        member_id: payload.id,
        member_password: payload.password
      })
      .then((result) => {
          if (result.status === 200) {
            if (result.data[0] == 0 ) {
              console.log(result.data)
              state.loginResult = 'true'
              state.loginToken = result.data[1]
              console.log(state.loginResult)
              console.log(state.loginToken)
              localStorage.setItem('loginAuth', state.loginResult)
              localStorage.setItem('loginToken', state.loginToken)
              alert('로그인 성공')
              router.push('MemoApp')
            } else if (result.data[0] == 1) {
              console.log(result.data)
              state.loginResult = false
              console.log(state.loginResult)
              alert("아이디 또는 비밀번호가 일치하지 않습니다.")
            }
          } else {
            alert("로그인 실패하였습니다. 관리자에게 문의해주세요.")
          }
      })
      .catch(e => {
          alert("Error : 관리자에게 문의해주세요.")
          console.log(e)
      }) 
    },
  /**
   * 로그아웃
   *
   * @param {object} state
   * @param {array} payload
   */
    Logout (state, payload) {
      console.log('## Logout ##')
      console.log(payload.MemberToken)
      axios.post('http://localhost:9090/webservice/Logout', { 
          member_token: payload.MemberToken,
        })
        .then((result) => {
          console.log(result)
          if (result.status === 200) {
            if (result.data === 0) {
              localStorage.removeItem('loginAuth');
              localStorage.removeItem('loginToken');
              state.loginResult = 'false'
              state.loginToken = ''
              alert("로그아웃을 완료하였습니다.")
              router.push('Login')
            } else if ( result.data === 100 ) {
              localStorage.removeItem('loginAuth');
              localStorage.removeItem('loginToken');
              state.loginResult = false
              state.loginToken = ''
              alert("세션이 만료되었습니다. 다시 로그인을 해주세요.")
              router.push('Login')
            } else {
              alert('로그아웃 실패. 관리자에게 문의해주세요.')
              router.push('Login')
            }
          }
        })
        .catch(e => {
            alert("Error : 관리자에게 문의해주세요.")
            console.log(e)
        }) 
    }
  },
  
    /**
     * component의 method와 유사한 역할을 합니다.
     * 비동기 처리를 합니다.
     * this.$store.dispatch('sample/getTodos, [payload])
     */
    actions: {}
  }
  